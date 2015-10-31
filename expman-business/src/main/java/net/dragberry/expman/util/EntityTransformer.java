package net.dragberry.expman.util;

import java.io.Serializable;

import net.dragberry.expman.bean.TransferObject;

public interface EntityTransformer<O extends Serializable, B extends TransferObject> {

	O transform(B bean);

	B transform(O object);
}
