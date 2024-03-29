package peaksoft.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.model.House;
import peaksoft.repository.AgencyRepository;
import peaksoft.repository.HouseRepository;
import peaksoft.services.HouseServices;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseServicesImpl implements HouseServices {
    private final HouseRepository houseRepository;
    private final AgencyRepository agencyRepository;

    @Override
    public void saveHouse(Long agencyId, House house) throws MyException {
        Agency agency = agencyRepository.findById(agencyId).orElseThrow(() -> new MyException("Agency by id: " + agencyId + " is not found!!!"));
        System.out.println(agency.getName());
        System.out.println(1);
        if (!house.getAddress().isBlank()) {
            System.out.println(2);
            if (house.getPrice() >= 0) {
                System.out.println(3);
                if (house.getRoom() > 0) {
                    System.out.println(4);
                    if (!house.getCountry().isBlank()) {
                        System.out.println(5);
                        house.setAgency(agency);
                    }
                    houseRepository.save(house);
                }
            }
        }
    }


    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    @Override
    public House getHouseById(Long id) throws MyException {
        return houseRepository.findById(id).orElseThrow(() -> new MyException("House by id: " + id + " is not found!!!"));
    }

    @Override
    public void updateHouseById(Long id, House house) throws MyException {
        House house1 = houseRepository.findById(id).orElseThrow(() -> new MyException("House by id: " + id + " is not found!!!"));
        house.setId(house1.getId());
        houseRepository.save(house);

    }

    @Override
    public void deleteHouseById(Long id) throws MyException {
        House house = houseRepository.findById(id).orElseThrow(() -> new MyException("House by id: " + id + " is not found!!!"));
        houseRepository.delete(house);
    }

    @Override
    public List<House> sortHouseByHouseType(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("desc")) {
            return houseRepository.sortDesc();
        } else if (ascOrDesc.equalsIgnoreCase("asc")) {
            return houseRepository.sortAsc();
        } else {
            return null;
        }
    }

    @Override
    public List<House> searchHouse(String word) {
        return null;
    }
}
