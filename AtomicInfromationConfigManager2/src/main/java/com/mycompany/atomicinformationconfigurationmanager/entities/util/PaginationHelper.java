package com.mycompany.atomicinformationconfigurationmanager.entities.util;

import javax.faces.model.DataModel;

/**
 *  PaginationHelper Class. This class is a Utility class that is generated purely by NetBeans. 
 *  It provides various methods for navigating a DataModel so that it can be presented and navigated
 *  on screen presenting a controlled number of entities. The class is unmodified so no further description  
 *  of this class or its methods is provided. Further information please see
 *  https://netbeans.org/
 *  
 *  @author Oracle
 *  @version 1.0
 */

//START IDE GENERATED CODE
public abstract class PaginationHelper {

    private int pageSize;
    private int page;

    public PaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public abstract int getItemsCount();

    public abstract DataModel createPageDataModel();

    public int getPageFirstItem() {
        return page * pageSize;
    }

    public int getPageLastItem() {
        int i = getPageFirstItem() + pageSize - 1;
        int count = getItemsCount() - 1;
        if (i > count) {
            i = count;
        }
        if (i < 0) {
            i = 0;
        }
        return i;
    }

    public boolean isHasNextPage() {
        return (page + 1) * pageSize + 1 <= getItemsCount();
    }

    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
    }

    public boolean isHasPreviousPage() {
        return page > 0;
    }

    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
        }
    }

    public int getPageSize() {
        return pageSize;
    }
}
//END IDE GENERATED CODE