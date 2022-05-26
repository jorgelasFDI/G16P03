package auxiliar.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import auxiliar.TernaryOperator;

public class LogicalNode implements TernaryOperator<Boolean> {
	
	public static List<List<Integer>> combinaciones;
	public static List<Integer> solution;

	public static void setCombinaciones(List<List<Integer>> combinaciones) {
        LogicalNode.combinaciones = combinaciones;
    }

    public static void setSolution(List<Integer> solution) {
        LogicalNode.solution = solution;
    }

	public static Map<String, LogicalNode> map = new HashMap<>();

	public static Set<String> terminales = new HashSet<>();

	public static Map<String, TernaryOperator<Boolean>> funciones = new HashMap<>(){{
		put("AND", (a, b, c) -> (a && b));
		put("OR", (a, b, c) -> (a || b));
		put("NOT", (a, b, c) -> (!a));
		put("IF", (a, b, c) -> (a ? b : c));
	}};

	public static Map<String, Integer> funcionesOperandos = new HashMap<>(){{
		put("AND", 2);
		put("OR", 2);
		put("NOT", 1);
		put("IF", 3);
	}};

	

	public static void clear() {
		map.clear();
		terminales.clear();
	}

	public static boolean add(String key) {
		if (map.containsKey(key)) return false;
		if (funciones.containsKey(key)) {
			map.put(key, new LogicalNode("NODE", funciones.get(key), funcionesOperandos.get(key)));
		} else {
			map.put(key, new LogicalNode("LEAF", null, terminales.size()));
			terminales.add(key);
		} return true;
	}
	
	private TernaryOperator<Boolean> operator;
	public String type;
	public int index;

	private LogicalNode(String type, TernaryOperator<Boolean> operator, int index) {
		this.type = type;
		this.operator = operator;
		this.index = index;
	}

	@Override
	public Boolean ternary(Boolean x, Boolean y, Boolean z) {
		return operator.ternary(x, y, z);
	}

}
