package shop.data;


import java.util.HashMap;

import shop.command.RerunnableCommand;
import shop.command.UndoableCommand;

public class Data {
	
	static HashMap<Integer,Video> videos = new HashMap<Integer,Video>();
			private Data() {}
			
			static public final Inventory newInventory() {
				return new InventorySet();
			}

			
			static public Video newVideo(String title, int year, String director) {
				Video v = new VideoObj(title,year,director);
				if(videos.containsValue(v)) {
					return videos.get(v.hashCode());
					
				}
				else {
					videos.put(v.hashCode(), v);
					return videos.get(v.hashCode());
				}
			}
			static public UndoableCommand newAddCmd(Inventory inventory, Video video, int change) {
				if (!(inventory instanceof InventorySet))
					throw new IllegalArgumentException();
				return new CmdAdd((InventorySet) inventory, video, change);
			}

			
			static public UndoableCommand newOutCmd(Inventory inventory, Video video) {
				if (!(inventory instanceof InventorySet))
					throw new IllegalArgumentException();
				return new CmdOut((InventorySet) inventory, video);
			}

			
			static public UndoableCommand newInCmd(Inventory inventory, Video video) {
				if (!(inventory instanceof InventorySet))
					throw new IllegalArgumentException();
				return new CmdIn((InventorySet) inventory, video);
			}

			
			static public UndoableCommand newClearCmd(Inventory inventory) {
				if (!(inventory instanceof InventorySet))
					throw new IllegalArgumentException();
				return new CmdClear((InventorySet) inventory);
			}
			
			static public RerunnableCommand newUndoCmd(Inventory inventory) {
				if (!(inventory instanceof InventorySet))
					throw new IllegalArgumentException();
				return ((InventorySet)inventory).getHistory().getUndo();
			}
			
			static public RerunnableCommand newRedoCmd(Inventory inventory) {
				if (!(inventory instanceof InventorySet))
					throw new IllegalArgumentException();
				return ((InventorySet)inventory).getHistory().getRedo();
			}
		
		}
