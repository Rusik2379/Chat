package Sber.Sber.auth.service;

import Sber.Sber.auth.request.*;
import Sber.Sber.auth.response.AuthResponse;
import Sber.Sber.models.*;
import Sber.Sber.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RealtyService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RealtyRepository realtyRepository;
    private final ImageRepository imageRepository;
    private final ClientRepository clientRepository;

    public AuthResponse createRealty(CreateRealtyRequest createRealtyRequest, List<MultipartFile> images) throws IOException {

        Company company = companyRepository.findByCompanyname(createRealtyRequest.getCompanyname())
                .orElseThrow(() -> new RuntimeException("Компания не найдена"));

        User user = userRepository.findById(createRealtyRequest.getId_user())
                .orElseThrow(() -> new RuntimeException("Автор не найден"));


        // Создаем новую недвижимость
        Realty realty = Realty.builder()
                .realtyname(createRealtyRequest.getRealtyname())
                .price(createRealtyRequest.getPrice())
                .floor(createRealtyRequest.getFloor())
                .square(createRealtyRequest.getSquare())
                .type(createRealtyRequest.getType())
                .adress(createRealtyRequest.getAdress())
                .year(createRealtyRequest.getYear())
                .status("В продаже")
                .companyForRealty(company)
                .allfloor(createRealtyRequest.getAllfloor())
                .numberRooms(createRealtyRequest.getNumberRooms())
                .description(createRealtyRequest.getDescription())
                .user(user)
                .images(new ArrayList<>()) // Initialize the images list
                .build();

        for (MultipartFile image : images) {
            if (image.getSize()!= 0) {
                Image imageEntity = toImageEntity(image);
                realty.addImageToProduct(imageEntity);
            }
        }

        Realty realtyFromDb = realtyRepository.save(realty);
        if (!realty.getImages().isEmpty()) {
            realtyFromDb.setPreviewImageId(realtyFromDb.getImages().getFirst().getId());
        }

        // сохраяем недвижимость в базе данных
        realtyRepository.save(realty);

        return AuthResponse.builder()
                .message("Realty created successfully")
                .response(HttpStatus.OK)
                .build();
    }

//    public AuthResponse sellRealty(SellRealtyRequest sellRealtyRequest){
//        User user = userRepository.findById(sellRealtyRequest.getId_user()).orElseThrow(() -> new RuntimeException("User not found"));
//        Client client = clientRepository.findById(sellRealtyRequest.getId_client()).orElseThrow(() -> new RuntimeException("Client not found"));
//        Realty realty = realtyRepository.findById(sellRealtyRequest.getId_realty()).orElseThrow(() -> new RuntimeException("Realty not found"));
//
//        realty.setSellerId(sellRealtyRequest.getId_user());
//        realty.setClient(client); // use setter method
//        realty.setStatus("Продано");
//
//        realtyRepository.save(realty);
//
//        return AuthResponse.builder()
//                .message("Realty sold successfully")
//                .response(HttpStatus.OK)
//                .build();
//    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setIsPreviewImage(true);
        return image;
    }

    public List<RealtyRepository.RealtyProjection> listRealtyCompany(String companyname) {
        return realtyRepository.findByCompanyForRealty_Companyname(companyname);
    }

    public List<ImageRepository.ImageProjection> findByRealtyId(Long id) {
        Realty realty = realtyRepository.findById(id).orElseThrow(() -> new RuntimeException("Realty not found"));
        return imageRepository.findByRealty(realty);
    }
}
