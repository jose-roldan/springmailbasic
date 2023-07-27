/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.egomusic.testimgspring.controller;

import java.awt.image.BufferedImage;

import ar.com.egomusic.testimgspring.service.ImgGenerator;
import ar.com.egomusic.testimgspring.util.ResponseMyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jose.roldan
 */
@RestController
@RequestMapping("/img")
public class ImgController {

    @Autowired
    ResponseMyUtil responseMyUtil;

    @Autowired
    ImgGenerator imgGenerator;

    @GetMapping(value = "/jpg/{number}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getUPCABarcode(@PathVariable("number") String numberText) throws Exception {
        return responseMyUtil.okResponse(imgGenerator.getJpgImage(numberText));
    }

    @GetMapping(value = "/png/{number}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> getEAN13Barcode(@PathVariable("number") String numberText) throws Exception {
        return responseMyUtil.okResponse(imgGenerator.getPngImage(numberText));
    }

}
