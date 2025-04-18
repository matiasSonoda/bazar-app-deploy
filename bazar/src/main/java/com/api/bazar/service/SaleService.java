
package com.api.bazar.service;

import com.api.bazar.entity.Customer;
import com.api.bazar.entity.Product;
import com.api.bazar.entity.ProductSale;
import com.api.bazar.entity.Sale;
import com.api.bazar.entity.dto.CustomerDto;
import com.api.bazar.entity.dto.ProductDto;
import com.api.bazar.entity.dto.SaleDto;
import com.api.bazar.exception.CustomerNotFoundException;
import com.api.bazar.exception.SaleNotFoundException;
import com.api.bazar.repository.CustomerRepository;
import com.api.bazar.repository.ProductRepository;
import com.api.bazar.repository.ProductSaleRepository;
import com.api.bazar.repository.SaleRepository;
import com.api.bazar.utils.SaleUtils;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.bazar.entity.dto.HighestSaleDetailsDto;
import java.util.Comparator;

@Service
public class SaleService {
    
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductSaleRepository productSaleRepository;
    @Autowired
    private SaleUtils saleUtils;
    
    @Transactional
    public Sale saveSale(SaleDto saleDto){
        //Declaracion de las variables
        Sale sale;
        if(saleDto.getIdSale() != null){
            sale = saleRepository.findById(saleDto.getIdSale())
                    .orElseThrow(() -> new SaleNotFoundException("No se encontro la venta correspondiente: " + saleDto.getIdSale()));
        }else{
            sale = new Sale();
        }
        List<Product> products = new ArrayList<>();
        List<ProductSale> listProdSale = new ArrayList<>();
        //Valida si el dto ya tiene id, eso significa que viene por parte de una solicitud PUT
        if(saleDto.getIdSale() != null){
            sale.setIdSale(saleDto.getIdSale());
        }
        //Busco el cliente y si existe lo guardo en un objeto
        
        Customer customer = customerRepository.findById(saleDto.getCustomer().getIdCustomer())
                .orElseThrow(() -> new CustomerNotFoundException("no se encontro el cliente"));
        //Agrego el cliente a la instancia de Sale
        sale.setCustomer(customer);
        //Agrego la fecha de la venta en la instancia de Sale
        sale.setDateSale(saleDto.getDateSale());
        
        ///////////////////////////////////////////////////////////////////////////////////////////
        //                                                                                       //
        //   AGREGO LOS PRODUCTOS DEL DTO A LA LISTA DE PRODUCTS SI EXISTEN EN LA BASE DE DATOS  //
        //   AGREGO LOS PRODUCTOS DEL DTO A LA LISTA DE PRODUCTS SI EXISTEN EN LA BASE DE DATOS  //
        //                                                                                       //
        ///////////////////////////////////////////////////////////////////////////////////////////       
        products = saleUtils.addProducts(saleDto, sale);
        
        //////////////////////////////////////////////////////////////
        //                                                          //
        //          CALCULO EL VALOR TOTAL DE LA VENTA              //
        //          CALCULO EL VALOR TOTAL DE LA VENTA              //
        //                                                          //
        //////////////////////////////////////////////////////////////
        BigDecimal totalPrice = saleUtils.totalPrice(products, saleDto);
        
        sale.setTotal(totalPrice);
        
        Sale actualSale = saleRepository.save(sale);
       
        ///////////////////////////////////////////////////////////////
        //                                                           //
        //    AGREGO LA LISTA DE PRODUCTSALE A LA INSTANCIA DE SALE  //
        //    AGREGO LA LISTA DE PRODUCTSALE A LA INSTANCIA DE SALE  //
        //                                                           //
        ///////////////////////////////////////////////////////////////
        saleUtils.buildProductSale(products, listProdSale, actualSale, saleDto);
        
        return saleRepository.save(actualSale);
    }
    
    public List<SaleDto> getAllSales(){
        List<Sale> sales = saleRepository.findAll();
        List<SaleDto> response = sales.stream().map((sale)->{
            SaleDto aux = new SaleDto();
            
            CustomerDto customer = new CustomerDto();
            customer.setIdCustomer(sale.getCustomer().getIdCustomer());
            customer.setDni(sale.getCustomer().getDni());
            customer.setName(sale.getCustomer().getName());
            customer.setLastName(sale.getCustomer().getLastName());
            
            List<ProductDto> products = sale.getProducts().stream().map((product)->{
                ProductDto dto = new ProductDto();
                dto.setIdProduct(product.getProduct().getIdProduct());
                dto.setName(product.getProduct().getName());
                dto.setCost(product.getProduct().getCost());
                dto.setBrand(product.getProduct().getBrand());
                dto.setQuantity(product.getQuantity());
                return dto;
            }).collect(Collectors.toList());
            
            aux.setIdSale(sale.getIdSale());
            aux.setCustomer(customer);
            aux.setDateSale(sale.getDateSale());
            aux.setProducts(products);
            aux.setTotal(sale.getTotal());
            return aux;
        }).collect(Collectors.toList());
    
        return response;
    }
    
    public SaleDto getSale(Long id){
        if(!saleRepository.existsById(id)){
            throw new SaleNotFoundException("No se encontro la venta solicitada: " + id);
        }
        Optional<Sale> sale = saleRepository.findById(id);
            SaleDto response = new SaleDto();

            CustomerDto customer = new CustomerDto();
            customer.setIdCustomer(sale.get().getCustomer().getIdCustomer());
            customer.setDni(sale.get().getCustomer().getDni());
            customer.setName(sale.get().getCustomer().getName());
            customer.setLastName(sale.get().getCustomer().getLastName());

            List<ProductDto> products = sale.get().getProducts().stream().map((product)->{
                    ProductDto dto = new ProductDto();
                    dto.setIdProduct(product.getProduct().getIdProduct());
                    dto.setName(product.getProduct().getName());
                    dto.setCost(product.getProduct().getCost());
                    dto.setBrand(product.getProduct().getBrand());
                    dto.setQuantity(product.getQuantity());
                    return dto;
                }).collect(Collectors.toList());

            response.setIdSale(sale.get().getIdSale());
            response.setCustomer(customer);
            response.setDateSale(sale.get().getDateSale());
            response.setProducts(products);
            response.setTotal(sale.get().getTotal());
            
            return response;
    }
    
    public List<ProductDto> getProductsSale(Long id){
        SaleDto dto = getSale(id);
        List<ProductDto> response = new ArrayList<>();
        response.addAll(dto.getProducts());
        return response;
    }
        
    public SaleDto getTotalSales(LocalDate date){
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        List<Sale> sales = saleRepository.findByDateSaleBetween(start, end);
        return sales.stream()
                .reduce(new SaleDto(), 
                        (dto, saleItem)->{
                            BigDecimal totalPrice = dto.getTotal().add(saleItem.getTotal());
                            dto.setTotal(totalPrice);
                            dto.setTotalSales(dto.getTotalSales() + 1);
                            return dto;}
                        , (dto1,dto2) ->{
                            dto1.setTotal(dto1.getTotal().add(dto2.getTotal()));
                            dto1.setTotalSales(dto1.getTotalSales() + dto2.getTotalSales());
                            return dto1;}
                );
    }
    
    public HighestSaleDetailsDto getHighestSaleDetails(){
        List<Sale> request = saleRepository.findAll();
        if(request.isEmpty()){
            throw new RuntimeException("Lista de ventas vacia en: getHighestSaleDetails");
        }
        Sale highestSale = request.stream()
                .max(Comparator.comparing(Sale::getTotal))
                .orElseThrow();
        
       HighestSaleDetailsDto response = new HighestSaleDetailsDto();
                    response.setIdSale(highestSale.getIdSale());
                    response.setName(highestSale.getCustomer().getName());
                    response.setLastName(highestSale.getCustomer().getLastName());
                    response.setTotal(highestSale.getTotal());
                    int quantity = highestSale.getProducts().stream().mapToInt(product-> product.getQuantity()).sum();
                    response.setProductsQuantity(quantity);
                
        return response;
    }
    
    public void deleteSale(Long id){
        if (!saleRepository.existsById(id)){
            throw new SaleNotFoundException("No se encontro la venta para borrarla: " + id);
        }
        saleRepository.deleteById(id);
    }
    
    public Sale updateSale(Long id, SaleDto saleDto){
        if (!saleRepository.existsById(id)){
            throw new SaleNotFoundException("No se encontro la venta para actualizarla: " + saleDto.getIdSale());
        }
                
        if (saleDto.getIdSale() == null || !id.equals(saleDto.getIdSale())){
            throw new IllegalArgumentException("Credenciales inalidas para actualizar la venta");
        }
        
        return saveSale(saleDto);
    }
}
