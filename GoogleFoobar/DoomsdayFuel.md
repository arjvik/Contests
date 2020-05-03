# Doomsday Fuel
Level: 4
Time to solve: 96 hours.

## Description
Making fuel for the LAMBCHOP's reactor core is a tricky process because of the exotic matter involved. It starts as raw ore, then during processing, begins randomly changing between forms, eventually reaching a stable form. There may be multiple stable forms that a sample could ultimately reach, not all of which are useful as fuel.

Commander Lambda has tasked you to help the scientists increase fuel creation efficiency by predicting the end state of a given ore sample. You have carefully studied the different structures that the ore can take and which transitions it undergoes. It appears that, while random, the probability of each structure transforming is fixed. That is, each time the ore is in 1 state, it has the same probabilities of entering the next state (which might be the same state).  You have recorded the observed transitions in a matrix. The others in the lab have hypothesized more exotic forms that the ore can become, but you haven't seen all of them.

Write a function solution(m) that takes an array of array of nonnegative ints representing how many times that state has gone to the next state and return an array of ints for each terminal state giving the exact probabilities of each terminal state, represented as the numerator for each state, then the denominator for all of them at the end and in simplest form. The matrix is at most `10` by `10`. It is guaranteed that no matter which state the ore is in, there is a path from that state to a terminal state. That is, the processing will always eventually end in a stable state. The ore starts in state `0`. The denominator will fit within a signed 32-bit integer during the calculation, as long as the fraction is simplified regularly.

For example, consider the matrix `m`:
```python
[
  [0,1,0,0,0,1],  # s0, the initial state, goes to s1 and s5 with equal probability
  [4,0,0,3,2,0],  # s1 can become s0, s3, or s4, but with different probabilities
  [0,0,0,0,0,0],  # s2 is terminal, and unreachable (never observed in practice)
  [0,0,0,0,0,0],  # s3 is terminal
  [0,0,0,0,0,0],  # s4 is terminal
  [0,0,0,0,0,0],  # s5 is terminal
]
```

So, we can consider different paths to terminal states, such as:
```
s0 -> s1 -> s3
s0 -> s1 -> s0 -> s1 -> s0 -> s1 -> s4
s0 -> s1 -> s0 -> s5
```
Tracing the probabilities of each, we find that
s2 has probability `0`
s3 has probability `3/14`
s4 has probability `1/7`
s5 has probability `9/14`
So, putting that together, and making a common denominator, gives an answer in the form of
`[s2.numerator, s3.numerator, s4.numerator, s5.numerator, denominator]` which is
`[0, 3, 2, 9, 14]`.

## Test Cases

### Test Case #1

Input:
```java
{{0, 2, 1, 0, 0}, {0, 0, 0, 3, 4}, {0, 0, 0, 0, 0}, {0, 0, 0, 0,0}, {0, 0, 0, 0, 0}}
```
Output:
```java
{7, 6, 8, 21}
```

### Test Case #2

Input:
```java
{{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}}
```
Output:
```java
{0, 3, 2, 9, 14}
```

## My Notes
(Note: not part of the problem description)

- We most likely want to replace the terminal rows in the given matrix with the same row from the identity matrix (a terminal state has 100% probability of transforming into itself). If we do this, the problem becomes an [Absorbing Markov Chain](https://en.wikipedia.org/wiki/Absorbing_Markov_chain)

- Let $T$ be the set of terminal states, and $U$ be the set of non-terminal states.
  $$
  P:U \times T \longrightarrow \R\\
  P(i \to j) \text{ represents the probability that a}\\\text{particle in state $i\in U$ transforms to state $j\in T$.}\\~\\
  P(i \to j) = m[i][j] + \sum_{k \in U} m[i][k] \cdot P(k \to j)
  $$
  This can become a system of $\|U\|\cdot\|T\|$ variables, or even $\|T\|$ systems of $\|U\|$ variables. Solving it in this manner can be done in $O(TU^3) = O(N^4)$. *(This was my failed Java solution)*

- If we convert the matrix $M$ into an Absorbing Markov Chain, we are looking to find the first row of
  $$
  M^\infty = \lim_{n\to\infty} M^n.
  $$
  This can be approximated to great accuracy (as a double, not as a rational) with a finite but large value `n` through repeated squaring.

- Using Absorbing Markov Chain properties, if we re-order the matrix to place the terminal states in the last rows and columns, we can split the matrix into four submatrices as follows:
  $$
  M = \begin{bmatrix}
  \mathbf Q & \mathbf R \\
  \mathbf 0 & \mathbf I
  \end{bmatrix}
  $$
  Then our answer is the first row of
  $$
  (\mathbf I-\mathbf Q)^{-1} \cdot \mathbf R
  $$
  (with $\mathbf I$ representing a different identity matrix in this equation).
  
- My failed Java solution works on 6/10 cases. However, when trying it using larger matrices, sometimes it outputs strange probability values, including occasionally a negative probability. In order to debug this, we can try generating large matrices using octave code such as the following Octave code:

  ```octave
  # 6 non-terminal states, 4 terminal states
  d = [6 4];
  
  # Calculate Java input
  M = [randi(10, d(1), sum(d)); zeros(d(2), sum(d))];
  str = "";
  for row=M'
      rowstr = "";
      for col=row'
          rowstr = [rowstr sprintf("%d", col) "," ];
      end
      str = [ str "{" rowstr "}, " ];
  end
  str = [ "new int[][] {" str "}" ];
  printf("%s\n\n", str);
  
  # Caulate correct output
  P = M ./ sum(M, 2);
  Q = P(1:6, 1:6);
  R = P(1:6, 7:10);
  N = pinv(eye(6)-Q);
  B = rats((N*R)(1, :))
  ```
  
- See https://surajshetiya.github.io/Google-foobar/ for working Python code
