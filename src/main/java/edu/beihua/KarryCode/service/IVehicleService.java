package edu.beihua.KarryCode.service;

import edu.beihua.KarryCode.entity.Admin;

public interface IVehicleService {
    void showAllVehicles_10in1();
    void queryByVehicleName_fuzzy(Admin admin);
    boolean vehicleInformationModifiedByLicense(Admin admin);
    boolean addVehicle(Admin admin);
}
