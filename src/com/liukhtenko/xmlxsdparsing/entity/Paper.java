package com.liukhtenko.xmlxsdparsing.entity;


public class Paper {
    private String title;
    private String type;
    private String subscriptionIndex;
    private boolean monthly;
    private int yearOfFoundation;
    private VisualCharacteristics visualCharacteristics = new VisualCharacteristics();

    public Paper() {
    }

    public Paper(String title, String type, String subscriptionIndex, boolean monthly,
                 int yearOfFoundation, VisualCharacteristics visualCharacteristics) {
        this.title = title;
        this.type = type;
        this.subscriptionIndex = subscriptionIndex;
        this.monthly = monthly;
        this.yearOfFoundation = yearOfFoundation;
        this.visualCharacteristics = visualCharacteristics;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubscriptionIndex() {
        return subscriptionIndex;
    }

    public void setSubscriptionIndex(String subscriptionIndex) {
        this.subscriptionIndex = subscriptionIndex;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    public VisualCharacteristics getVisualCharacteristics() {
        return visualCharacteristics;
    }

    public void setVisualCharacteristics(VisualCharacteristics visualCharacteristics) {
        this.visualCharacteristics = visualCharacteristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Paper paper = (Paper) o;
        return monthly == paper.monthly &&
                yearOfFoundation == paper.yearOfFoundation &&
                title.equals(paper.title) &&
                type.equals(paper.type) &&
                subscriptionIndex.equals(paper.subscriptionIndex) &&
                visualCharacteristics.equals(paper.visualCharacteristics);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((subscriptionIndex == null) ? 0 : subscriptionIndex.hashCode());
        result = prime * result + yearOfFoundation;
        result = prime * result + ((visualCharacteristics == null) ? 0 : visualCharacteristics.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Paper{title='").append(title).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", subscriptionIndex='").append(subscriptionIndex).append('\'');
        sb.append(", monthly=").append(monthly).append('\'');
        sb.append(", yearOfFoundation=").append(yearOfFoundation);
        sb.append(", visualCharacteristics=").append(visualCharacteristics).append('}');
        return sb.toString();
    }

    public static class VisualCharacteristics {
        private boolean colored;
        private int pageSize;
        private boolean glossy;

        public VisualCharacteristics() {
        }

        public VisualCharacteristics(boolean colored, int pageSize, boolean glossy) {
            this.colored = colored;
            this.pageSize = pageSize;
            this.glossy = glossy;
        }

        public boolean isColored() {
            return colored;
        }

        public void setColored(boolean colored) {
            this.colored = colored;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public boolean isGlossy() {
            return glossy;
        }

        public void setGlossy(boolean glossy) {
            this.glossy = glossy;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VisualCharacteristics that = (VisualCharacteristics) o;
            return colored == that.colored &&
                    pageSize == that.pageSize &&
                    glossy == that.glossy;
        }

        @Override
        public int hashCode() {
            int result = 31 + pageSize;
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("VisualCharacteristics{colored=").append(colored);
            sb.append(", pageSize=").append(pageSize);
            sb.append(", glossy=").append(glossy).append('}');
            return sb.toString();
        }
    }
}
