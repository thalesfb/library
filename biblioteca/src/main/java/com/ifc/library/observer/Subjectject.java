package com.ifc.library.observer;

public interface Subjectject {
  void addObserver(Observer observer);
  void removeObserver(Observer observer);
  void notifyObservers(String mensagem);
}
