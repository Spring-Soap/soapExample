package com.discovering.soap_producing_example.endpoints;

import com.discovering.soap_producing_example.entites.Product;
import com.discovering.soap_producing_example.repositories.ProductRepository;
import com.discovering.soapproducingexample.wsdl.GetProductRequest;
import com.discovering.soapproducingexample.wsdl.GetProductResponse;
import com.discovering.soapproducingexample.wsdl.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndPoint {

    private static final String NAMESPACE_URI = "http://www.discovering.com/soapProducingExample/wsdl";

    private final ProductRepository productRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductRequest")
    @ResponsePayload
    public GetProductResponse getProduct(@RequestPayload GetProductRequest productRequest) {
        Product product = productRepository.findByName(productRequest.getName()).
                orElseThrow(() ->  new RuntimeException("Product not found with name: " + productRequest.getName()));

        ProductType productType = new ProductType();
        productType.setId(product.getId());
        productType.setName(product.getName());
        productType.setPrice(product.getPrice());

        GetProductResponse response = new GetProductResponse();
        response.setProduct(productType);

        return response;
    }


}
