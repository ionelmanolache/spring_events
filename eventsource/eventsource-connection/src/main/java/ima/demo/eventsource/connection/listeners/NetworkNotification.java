package ima.demo.eventsource.connection.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import ima.demo.eventsource.common.events.NetworkEvent;

public class NetworkNotification {
	//TODO ConcurentHashmap ???
	private Map<Class<? extends NetworkEvent>, List<NetworkNotificationListener<? extends NetworkEvent>>> map = new HashMap<Class<? extends NetworkEvent>, List<NetworkNotificationListener<? extends NetworkEvent>>>();

	public <T extends NetworkEvent> void addListener(Class<? extends NetworkEvent> chanel, NetworkNotificationListener<? extends NetworkEvent> listener) {
		List<NetworkNotificationListener<? extends NetworkEvent>> list = map.get(chanel);
		if (list == null) {
			list = new CopyOnWriteArrayList<NetworkNotificationListener<? extends NetworkEvent>>();
			map.put(chanel, list);
		}
		list.add(listener);
	}

	public <T extends NetworkEvent> void publishEvent(T event) {
		Class<? extends NetworkEvent> chanel = event.getClass();
		List<NetworkNotificationListener<? extends NetworkEvent>> list = map.get(chanel);
		for (NetworkNotificationListener<? extends NetworkEvent> listener : list) {
			listener.receiveEvent(event);
		}
	}
	
	public void removeAllListener() {
		map.clear();
	}
}
