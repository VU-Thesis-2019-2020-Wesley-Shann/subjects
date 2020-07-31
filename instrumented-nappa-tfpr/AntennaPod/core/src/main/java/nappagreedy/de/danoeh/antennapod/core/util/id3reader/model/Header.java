package nappagreedy.de.danoeh.antennapod.core.util.id3reader.model;

public abstract class Header {

	final String id;
	final int size;

	Header(String id, int size) {
		super();
		this.id = id;
		this.size = size;
	}

	public String getId() {
		return id;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "Header [id=" + id + ", size=" + size + "]";
	}
	
	

}
