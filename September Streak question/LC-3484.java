class Spreadsheet {
    private final int rows;
    private final int cols = 26;
    private final int[][] grid;

    public Spreadsheet(int rows) {
        this.rows = rows;
        this.grid = new int[rows][cols]; // initialized to 0 by default
    }
    
    private int[] parseCell(String cell) {
        // cell format: Letter (A-Z) followed by 1-based row number, e.g. "A1", "B10"
        char colChar = cell.charAt(0);
        int col = colChar - 'A';
        int row = Integer.parseInt(cell.substring(1)) - 1;
        return new int[]{row, col};
    }
    
    public void setCell(String cell, int value) {
        int[] rc = parseCell(cell);
        grid[rc[0]][rc[1]] = value;
    }
    
    public void resetCell(String cell) {
        int[] rc = parseCell(cell);
        grid[rc[0]][rc[1]] = 0;
    }
    
    private int operandValue(String token) {
        token = token.trim();
        if (token.length() > 0 && Character.isLetter(token.charAt(0))) {
            int[] rc = parseCell(token);
            return grid[rc[0]][rc[1]];
        } else {
            return Integer.parseInt(token);
        }
    }
    
    public int getValue(String formula) {
        if (formula == null || formula.length() == 0) return 0;
        if (formula.charAt(0) != '=') return 0; // per spec formulas begin with '='
        String body = formula.substring(1); // remove '='
        String[] parts = body.split("\\+");
        int left = operandValue(parts[0]);
        int right = operandValue(parts[1]);
        return left + right;
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */
