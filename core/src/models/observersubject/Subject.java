package models.observersubject;

import java.util.ArrayList;

public class Subject {
	ArrayList<Observer> observers; 
	public Subject() { 
		observers = new ArrayList<Observer>(); 
	}
	public void NotifyAllObservers() { 
		 for(Observer o: observers)
			 o.NotifyObserver(this); 
	}
	public void AddObserver(Observer o) { 
		observers.add(o); 
	}
}
