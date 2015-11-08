package net.dragberry.expman.bean;

public class ResultTO<T extends TransferObject> extends AbstractResultTO<T> {

	private static final long serialVersionUID = -5024523531166601727L;

	private T object;
	
	public ResultTO(T resultObject) {
		this.object = resultObject;
	}
	
	public T getObject() {
		return object;
	}
	
}
