package com.discovering.soapexample.endpoints;

import com.discovering.soapexample.entites.Product;
import com.discovering.soapexample.repositories.ProductRepository;
import com.discovering.soapexample.wsdl.GetProductRequest;
import com.discovering.soapexample.wsdl.GetProductResponse;
import com.discovering.soapexample.wsdl.ProductType;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndPoint {

    private static final String NAMESPACE_URI = "http://www.discovering.com/soapExample/wsdl";

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
