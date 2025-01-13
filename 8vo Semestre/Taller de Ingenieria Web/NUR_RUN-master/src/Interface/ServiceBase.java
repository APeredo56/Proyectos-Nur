package Interface;

import Data.ManagerData;

public abstract class ServiceBase {

    protected ManagerData managerData;
    public ServiceBase(ManagerData managerData) {
        this.managerData = managerData;
    }
}
