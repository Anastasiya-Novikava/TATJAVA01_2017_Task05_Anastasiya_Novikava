package com.epam.task5.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Food implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String link;
	private String name;
	private HashMap<String, String> descriptionAndPrice = new HashMap<>();
    private ArrayList<Integer> listWeight = new ArrayList<>();
    private int count;

    public Food() {}
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, String> getDescriptionAndPrice() {
		return descriptionAndPrice;
	}

	public void setDescriptionAndPrice(HashMap<String, String> descriptionAndPrice) {
		this.descriptionAndPrice = descriptionAndPrice;
	}

	public ArrayList<Integer> getListWeight() {
		return listWeight;
	}

	public void setListWeight(ArrayList<Integer> arrayWeight) {
		this.listWeight = arrayWeight;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		Food food = (Food) obj;
	
		if (null == id) {
			return (id == food.id);
		} else {
			if (!id.equals(food.id)) {
				return false;
			}
		}

		if (null == link) {
			return (link == food.link);
		} else {
			if (!link.equals(food.link)) {
				return false;
			}
		}
		
		if (null == name) {
			return (name == food.name);
		} else {
			if (!name.equals(food.name)) {
				return false;
			}
		}

		if (null == descriptionAndPrice) {
			return (descriptionAndPrice == food.descriptionAndPrice);
		} else {
			if (!descriptionAndPrice.equals(food.descriptionAndPrice)) {
				return false;
			}
		}
		
		if (null == listWeight) {
			return (listWeight == food.listWeight);
		} else {
			if (!listWeight.equals(food.listWeight)) {
				return false;
			}
		}
		
		if (count != food.count) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = id == null ? 0 : id.hashCode();
		result = 31 * result + (link == null ? 0 : link.hashCode());
		result = 31 * result + (name == null ? 0 : name.hashCode());
		result = 31 * result + (descriptionAndPrice == null ? 0 : descriptionAndPrice.hashCode());
		result = 31 * result + (listWeight == null ? 0 : listWeight.hashCode());
		result = 31 * result + count;
		return result;
	}
	
	@Override
	public String toString() {
		return "Food [id=" + id + ", link=" + link + ", name=" + name + ", descriptionAndPrice=" + descriptionAndPrice
				+ ", listWeight=" + listWeight + ", count=" + count + "]";
	}

	public void addDescriptionAndPrice(String description, String price) {
        descriptionAndPrice.put(description, price);
    }
	
	public void addWeight(int weight) {
        this.listWeight.add(weight);
    }

}
