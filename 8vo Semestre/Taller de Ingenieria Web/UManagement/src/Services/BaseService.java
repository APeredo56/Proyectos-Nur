package Services;

import Data.DataManager;

public abstract class BaseService {

    protected DataManager dataManager;
    public BaseService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

}
