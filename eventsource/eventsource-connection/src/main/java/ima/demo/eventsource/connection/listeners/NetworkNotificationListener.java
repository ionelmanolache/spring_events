package ima.demo.eventsource.connection.listeners;

import ima.demo.eventsource.common.events.NetworkEvent;

public interface NetworkNotificationListener<T extends NetworkEvent> {
	void receiveEvent(T event);
}
