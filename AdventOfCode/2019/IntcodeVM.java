import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class IntcodeVM {
	private List<Integer> tape, code;
	private int pointer;
	private int mode, params; //internals
	
	private boolean outputOnlyZeros = true;
	private boolean inputLoops = false;
	
	public IntcodeVM(List<Integer> code) {
		this.code = code;
		reset();
	}
	
	public IntcodeVM(int... code) {
		this(Arrays.stream(code).mapToObj(Integer::valueOf).collect(Collectors.toList()));
	}
	
	public IntcodeVM(String code) {
		this(Arrays.stream(code.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
	}
	
	public IntcodeVM(int day) {
		this(ADVENTOFCODEINPUT.readLineList(day).get(0));
	}
	
	public void reset() {
		this.tape = new ArrayList<>(code);
		pointer = 0;
		mode = -1;
		params = -1;
	}
	
	public List<Integer> getTape() {
		return tape;
	}
	
	public void setOutputOnlyZeros(boolean outputOnlyZeros) {
		this.outputOnlyZeros = outputOnlyZeros;
	}

	public void setInputLoops(boolean inputLoops) {
		this.inputLoops = inputLoops;
	}
	
	public List<Integer> run(Integer... input) {
		return run(new LinkedList<Integer>(Arrays.asList(input)));
	}
	
	public List<Integer> runAndReset(Integer... input) {
		List<Integer> output = run(input);
		reset();
		return output;
	}

	private List<Integer> run(Queue<Integer> input) {
		List<Integer> output = new LinkedList<>();
		while (pointer != -1) {
			int opcode = tape.get(pointer);
			mode = opcode / 100;
			switch (opcode % 100) {
			case 99: hasParams(-99);
				pointer = -1;
				break;
			case 1: hasParams(3);
				setParam(3, getParam(1)+getParam(2));
				break;
			case 2: hasParams(3);
				setParam(3, getParam(1)*getParam(2));
				break;
			case 3: hasParams(1);
				if (inputLoops)
					input.add(input.peek());
				setParam(1, input.poll());
				break;
			case 4: hasParams(1);
				int x = getParam(1);
				if (!outputOnlyZeros || x != 0)
					output.add(x);
				break;
			case 5: hasParams(2);
			if (getParam(1) != 0) {
					pointer = getParam(2);
					hasParams(-99);
				}
				break;
			case 6: hasParams(2);
				if (getParam(1) == 0) {
					pointer = getParam(2);
					hasParams(-99);
				}
				break;
			case 7: hasParams(3);
				setParam(3, getParam(1) < getParam(2) ? 1 : 0);
				break;
			case 8: hasParams(3);
				setParam(3, getParam(1) == getParam(2) ? 1 : 0);
				break;
			default:
				throw new RuntimeException("Bad opcode");
			}
			jumpPastParams();
		}
		return output;
	}
	
	private int getParam(int param) {
		if (param > params || param <= 0)
			throw new RuntimeException("Bad param number");
		int type = (mode / expBase10(param - 1)) % 10;
		if (type == 0) // position mode
			return tape.get(tape.get(pointer+param));
		else if (type == 1) // immediate mode
			return tape.get(pointer+param);
		else
			throw new RuntimeException("Bad parameter type");
	}
	
	private void setParam(int param, int value) {
		if (param > params || param <= 0)
			throw new RuntimeException("Bad param number");
		int type = (mode / expBase10(param - 1)) % 10;
		if (type == 0) // position mode
			tape.set(tape.get(pointer+param), value);
		else // other modes
			throw new RuntimeException("Bad parameter type");
	}
	
	private void hasParams(int params) {
		this.params = params;
	}
	
	private void jumpPastParams() {
		if (params == -99) // magic number to not jump
			return;
		if (params == -1)
			throw new RuntimeException("Params has not been set");
		pointer += params + 1;
		params = -1;
	}
	
	private int expBase10(int b) {
		if (b == 0) return 1;
		if (b == 1) return 10;
		if (b == 2) return 100;
		if (b == 3) return 1000;
		if (b == 4) return 10000;
		int x = expBase10(b/2);
		return (b%2 == 0) ? x*x : 10*x*x;
	}
}
