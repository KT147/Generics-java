package genericsextra;

class LPAStudent extends Student {

    private double percentComplete;

    public LPAStudent() {
        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if (fieldName.equalsIgnoreCase("PercentComplete")) {
            double percent = Double.parseDouble(value);
            return this.getPercentComplete() <= percent;
        }

        return super.matchFieldValue(fieldName, value);
    }
}
