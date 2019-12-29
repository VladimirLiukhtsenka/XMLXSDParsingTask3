package com.liukhtenko.xmlxsdparsing.entity;

import java.util.Objects;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return monthly == paper.monthly &&
                yearOfFoundation == paper.yearOfFoundation &&  // FIXME: 27.12.2019
                Objects.equals(title, paper.title) &&
                Objects.equals(type, paper.type) &&
                Objects.equals(subscriptionIndex, paper.subscriptionIndex) &&
                Objects.equals(visualCharacteristics, paper.visualCharacteristics);
    }

    @Override
    public int hashCode() {  // FIXME: 27.12.2019
        return Objects.hash(title, type, subscriptionIndex, monthly, yearOfFoundation, visualCharacteristics);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "title='" + title + '\'' +  // FIXME: 27.12.2019
                ", type='" + type + '\'' +
                ", subscriptionIndex='" + subscriptionIndex + '\'' +
                ", monthly=" + monthly +
                ", yearOfFoundation=" + yearOfFoundation +
                ", visualCharacteristics=" + visualCharacteristics +
                '}';
    }

    public static class VisualCharacteristics {
        private boolean colored;
        private int pageSize;
        private boolean glossy;

        public VisualCharacteristics() {
        }

        public VisualCharacteristics(boolean colored, int pageSizec, boolean glossy) {
            this.colored = colored;
            this.pageSize = pageSizec;
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

        public void setPageSize(int pageSizec) {
            this.pageSize = pageSizec;
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
            VisualCharacteristics that = (VisualCharacteristics) o;  // FIXME: 27.12.2019
            return colored == that.colored &&
                    pageSize == that.pageSize &&
                    glossy == that.glossy;
        }

        @Override
        public int hashCode() {
            return Objects.hash(colored, pageSize, glossy); // FIXME: 27.12.2019
        }

        @Override
        public String toString() {  // FIXME: 27.12.2019
            return "VisualCharacteristics{" +
                    "colored=" + colored +
                    ", pageSize=" + pageSize +
                    ", glossy=" + glossy +
                    '}';
        }
    }
}
