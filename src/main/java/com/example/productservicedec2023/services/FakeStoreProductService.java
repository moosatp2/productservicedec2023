package com.example.productservicedec2023.services;

import com.example.productservicedec2023.dtos.FakeStoreProductDto;
import com.example.productservicedec2023.exceptions.productNotExistsException;
import com.example.productservicedec2023.models.Category;
import com.example.productservicedec2023.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImageUrl());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.getCategory().setId(fakeStoreProductDto.getId());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) throws productNotExistsException {
//        int a = 1/0;
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        if ( fakeStoreProductDto == null ){
            throw new productNotExistsException(
                    "product with id" + id + "not exist"
            );

        }
        return convertFakeStoreProductToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" , FakeStoreProductDto[].class );

        List<Product> answer = new ArrayList<>();

        for ( FakeStoreProductDto dto: fakeStoreProductDtos ){
            answer.add(convertFakeStoreProductToProduct(dto));
        }
        return answer;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());
       //fakeStoreProductDto.setCategory(product.getCategory().getName());


        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto1 =  restTemplate.execute( "https://fakestoreapi.com/products/"+ id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreProductToProduct(fakeStoreProductDto1);
    }

    @Override
    public Product addProduct(Product product) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImageUrl(product.getImageUrl());

        restTemplate.postForEntity("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
        return convertFakeStoreProductToProduct(fakeStoreProductDto);
    }

    @Override
    public Product deleteProduct(Long id) {

        restTemplate.delete("https://fakestoreapi.com/products/{id}");
        return null;
    }
}
