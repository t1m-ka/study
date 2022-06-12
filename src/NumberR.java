public enum NumberR {
    I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8), IX(9), X(10);

    private int num;

    NumberR(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }
    public static String contains(String operand) {
        for (NumberR op: NumberR.values()) {
            if (op.name().equals(operand)) {
                return "rim";
            }
            if (Integer.toString(op.getNum()).equals(operand)) {
                return "arb";
            }
        }
        return null;
    }

}
