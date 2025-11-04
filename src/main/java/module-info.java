module module_name {

    requires javafx.controls;

    // To make Lab 2 code work...
    requires java.desktop;
    requires java.net.http;
    requires JColor;

    exports lab3;
    exports lab3.ui;
}