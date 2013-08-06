package org.gnu.gnucash;

import org.w3c.dom.Node;

public interface INodeWorker {

	public void doWork(int index, Node node, Object... data) throws Exception;
}
