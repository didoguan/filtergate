package com.deepspc.filtergate.modular.system.model;

import java.util.List;

public interface Tree {

    String getNodeId();

    String getNodeParentId();

    void setChildrenNodes(List childrenNodes);
}
