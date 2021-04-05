module TicketingSystem {
	exports gui;
	exports main;
	exports domain;
	exports exceptions;
	exports testing;
	exports controller;
	exports controller_interfaces;
	exports repository;
	exports Constants;
	exports Helpers;
	exports Providers;

	requires java.sql;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.swing;
	requires java.persistence;
	requires com.microsoft.sqlserver.jdbc;
	requires junit;
	requires org.junit.jupiter.api;
	requires org.mockito;
	requires mockito.junit.jupiter;
	requires org.junit.jupiter.params;
	requires java.instrument;
	
	opens gui to javafx.graphics, javafx.fxml;
	opens domain;
}