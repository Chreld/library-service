package dk.via.pro3.libraryservice.service;

import dk.via.pro3.libraryservice.dto.BorrowerDTO;
import dk.via.pro3.libraryservice.dto.ResponseDTO;
import dk.via.pro3.libraryservice.dto.VinylDTO;
import dk.via.pro3.libraryservice.model.Borrower;
import dk.via.pro3.libraryservice.repository.BorrowerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {
    private BorrowerRepository borrowerRepository;
    private RestClient restClient;


    public LibraryServiceImpl(BorrowerRepository borrowerRepository, RestClient restClient) {
        this.borrowerRepository = borrowerRepository;
        this.restClient = restClient;
    }

    @Override
    public ResponseDTO getVinylBorrower(Long id) {
        Borrower borrower = borrowerRepository.findById(id);
        BorrowerDTO borrowerDTO = mapToBorrower(borrower);

        VinylDTO vinylDTO = restClient.get()
                .uri("/api/v1/vinyls/{id}", borrower.getVinylId())
                .retrieve()
                .body(VinylDTO.class);
        ResponseDTO responseDTO = new ResponseDTO(vinylDTO, borrowerDTO);
        return responseDTO;
    }

    @Override
    public List<Borrower> getBorrowers() {
        return borrowerRepository.findAll();
    }

    private BorrowerDTO mapToBorrower(Borrower borrower) {
        BorrowerDTO borrowerDTO = new BorrowerDTO(borrower.getId(),
                borrower.getFirstName(),
                borrower.getLastName(),
                borrower.getEmail());
        return borrowerDTO;
    }
}
