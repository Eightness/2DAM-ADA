package org.albert.providers;

import java.util.List;

public interface ViewInterface<Entity> {
    void showEntity(Entity entity);
    void showEntities(List<Entity> entities);
}
