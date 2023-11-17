package edu.project2.solvers;

import java.util.HashMap;

public class SolverFactory {
    private final HashMap<Integer, Solver> solvers = new HashMap<>();

    public Solver getSolver(int solverCode) {
        Solver solver = solvers.get(solverCode);
        if (solver == null) {
            solver = switch (solverCode) {
                case 1 -> new BFSAlgorithmSolver();
                case 2 -> new DFSAlgorithmSolver();
                default -> throw new IllegalStateException("Неверный выбор алгоритма решения: " + solverCode);
            };
            solvers.put(solverCode, solver);
        }
        return solver;
    }
}
