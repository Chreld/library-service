package dk.via.pro3.libraryservice.service;

import dk.via.pro3.libraryservice.dto.BorrowerDTO;
import dk.via.pro3.libraryservice.dto.ResponseDTO;
import dk.via.pro3.libraryservice.model.Borrower;

import java.util.List;

public interface LibraryService {
    ResponseDTO getVinylBorrower(Long id);
    List<Borrower> getBorrowers();

}
