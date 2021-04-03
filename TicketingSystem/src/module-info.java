module sokoban88 {
	//exports persistentie;
	//exports ui;
	//exports util;
	exports gui;
	exports main;
	exports domain;
	//exports exceptions;

	requires java.sql;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.swing;
	requires java.persistence;
	requires com.microsoft.sqlserver.jdbc;
	
	opens gui to javafx.graphics, javafx.fxml;
}