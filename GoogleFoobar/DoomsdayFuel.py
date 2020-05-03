"""
This problem represents an Absorbing Markov Chain.
To solve it, we simply need to find the absorbance probability of the chain.

References:
https://en.wikipedia.org/wiki/Absorbing_Markov_chain

Formula:

Let M = [[ [Q] [R]
           [0] [I] ]

where Q and R are sub-matrixes, I is the identity matrix, and 0 is the zero matrix.
In this problem, I is actually filled with zeros in the input.

Our answer is the first row of ((I-Q)^-1) * R

Credit to https://surajshetiya.github.io/Google-foobar/ for matrix subroutines.

"""

from fractions import Fraction

_gcd = lambda x, y: x if y == 0 else _gcd(y, x % y)
gcd = lambda x, y: _gcd(abs(x), abs(y))
lcm = lambda x, y: x*y/gcd(x, y)

deepcopy = lambda m: [[x for x in row] for row in m]

transpose = lambda m: [[row[i] for row in m] for i in range(len(m))]

def reorder_matrix(mat):
    sums = [sum(row) for row in mat]
    terminal_states = [i for i in range(len(sums)) if not sums[i]]
    mat2 = [[(Fraction(x, sums[i]) if sums[i] != 0 else Fraction(0, 1)) for x in row] for i, row in enumerate(mat)]
    nonterminal_mat = []
    terminal_mat = []
    for i in range(len(mat2)):
        if i not in terminal_states:
            nonterminal_mat.append(mat2[i])
        else:
            terminal_mat.append(mat2[i])
    nonterminal_mat.extend(terminal_mat)
    nonterminal_mat_sorted = []
    for i, row in enumerate(nonterminal_mat):
        row_sorted = []
        terminal_rows = []
        for j in range(len(nonterminal_mat)):
            if j not in terminal_states:
                row_sorted.append(row[j])
            else:
                terminal_rows.append(row[j])
        row_sorted.extend(terminal_rows)
        nonterminal_mat_sorted.append(row_sorted)
    return [nonterminal_mat_sorted, len(terminal_mat)]
    
def gauss_elmination(m, values):
    mat = deepcopy(m)
    for i in range(len(mat)):
        index = -1
        for j in range(i, len(mat)):
            if mat[j][i].numerator != 0:
                index = j
                break
        if index == -1:
            raise ValueError('Non-Invertible Matrix')
        mat[i], mat[index] = mat[index], mat[j]
        values[i], values[index] = values[index], values[i]
        for j in range(i+1, len(mat)):
            if mat[j][i] == 0:
                continue
            ratio = -mat[j][i]/mat[i][i]
            for k in range(i, len(mat)):
                mat[j][k] += ratio * mat[i][k]
            values[j] += ratio * values[i]
    res = [0 for i in range(len(mat))]
    for i in range(len(mat)):
        index = len(mat) -1 -i
        end = len(mat) - 1
        while end > index:
            values[index] -= mat[index][end] * res[end]
            end -= 1
        res[index] = values[index]/mat[index][index]
    return res
    
def inverse(mat):
    mat2 = transpose(mat)
    mat_inv = []
    for i in range(len(mat2)):
        values = [Fraction(int(i==j), 1) for j in range(len(mat))]
        mat_inv.append(gauss_elmination(mat2, values))
    return mat_inv

def multiply(m1, m2):
    ans = []
    for i in range(len(m1)):
        m = []
        for j in range(len(m2[0])):
            f = Fraction(0, 1)
            for k in range(len(m1[0])):
                f += m1[i][k]*m2[k][j]
            m.append(f)
        ans.append(m)
    return ans

def seperateQR(mat, len_r):
    # Q and R are defined as in the Definition of a Absorbing Markov Chain
    len_q = len(mat) - len_r
    Q = [[int(i==j)-mat[i][j] for j in range(len_q)] for i in range(len_q)]
    R = [mat[i][len_q:] for i in range(len_q)]
    return (Q, R)

def solution(mat):
    res = reorder_matrix(mat)
    if res[1] == len(mat):
        return [1, 1]
    Q, R = seperateQR(*res)
    inv = inverse(Q)
    res = multiply(inv, R)
    row = res[0]
    curr_lcm = 1
    for item in row:
        curr_lcm = lcm(curr_lcm, item.denominator)
    return ([int(x*curr_lcm) for x in row] + [curr_lcm])
