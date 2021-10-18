package ru.job4j.stream;

public class Notebook {
    private String brand;
    private double screen;
    private String processor;
    private String card;
    private int storage;
    private boolean ssd;
    private boolean gaming;

    @Override
    public String toString() {
        return "Notebook{"
                + "brand='" + brand + '\''
                + ", screen=" + screen
                + ", processor='" + processor
                + '\'' + ", card='" + card + '\''
                + ", storage=" + storage
                + ", ssd=" + ssd
                + ", gaming=" + gaming + '}';
    }

    static class Builder {
        private String brand;
        private double screen;
        private String processor;
        private String card;
        private int storage;
        private boolean ssd;
        private boolean gaming;

        Builder buildBrand(String brand) {
            this.brand = brand;
            return this;
        }

        Builder buildScreen(double screen) {
            this.screen = screen;
            return this;
        }

        Builder buildProcessor(String processor) {
            this.processor = processor;
            return this;
        }

        Builder buildCard(String card) {
            this.card = card;
            return this;
        }

        Builder buildStorage(int storage) {
            this.storage = storage;
            return this;
        }

        Builder buildSsd(boolean ssd) {
            this.ssd = ssd;
            return this;
        }

        Builder buildGaming(boolean gaming) {
            this.gaming = gaming;
            return this;
        }

        Notebook build() {
            Notebook notebook = new Notebook();
            notebook.brand = brand;
            notebook.card = card;
            notebook.processor = processor;
            notebook.screen = screen;
            notebook.storage = storage;
            notebook.gaming = gaming;
            notebook.ssd = ssd;
            return notebook;
        }
    }

    public static void main(String[] args) {
        Notebook notebook = new Builder().buildBrand("HP")
                .buildProcessor("Intel i7")
                .buildScreen(24)
                .buildCard("Nvidia GeForce 3080")
                .buildStorage(512)
                .buildGaming(true)
                .buildSsd(true)
                .build();
        System.out.println(notebook);
    }
}
