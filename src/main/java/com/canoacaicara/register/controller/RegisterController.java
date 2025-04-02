package com.canoacaicara.register.controller;

import com.canoacaicara.common.ApiReponse;
import com.canoacaicara.register.service.CreateRegister;
import com.canoacaicara.register.service.DeleteRegister;
import com.canoacaicara.register.service.GetRegister;
import com.canoacaicara.register.service.UpdateRegister;
import com.canoacaicara.register.controller.dto.AllRegistersResponse;
import com.canoacaicara.register.controller.dto.CreateRegisterRequest;
import com.canoacaicara.register.controller.dto.RegisterByMonthResponse;
import com.canoacaicara.register.controller.dto.RegisterResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("registers")
public class RegisterController {
    private final CreateRegister createRegister;
    private final GetRegister getRegister;
    private final UpdateRegister updateRegister;
    private final DeleteRegister deleteRegister;

    public RegisterController(CreateRegister createRegister, GetRegister getRegister, UpdateRegister updateRegister, DeleteRegister deleteRegister) {
        this.createRegister = createRegister;
        this.getRegister = getRegister;
        this.updateRegister = updateRegister;
        this.deleteRegister = deleteRegister;
    }

    @PostMapping()
    ResponseEntity<ApiReponse<RegisterResponse>> createRegister(
            @Valid @RequestBody CreateRegisterRequest request,
            @RequestHeader("Authorization") String token) throws Exception {
        ApiReponse<RegisterResponse> response = new ApiReponse<>("Register created", createRegister.createRegister(request, token));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping()
    ResponseEntity<ApiReponse<List<RegisterResponse>>> getUserRegisters(@RequestHeader("Authorization") String token) {
        ApiReponse<List<RegisterResponse>> response = new ApiReponse<>("Registers found", getRegister.getUserRegisters(token));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/all")
    ResponseEntity<ApiReponse<List<AllRegistersResponse>>> getAllRegisters() {
        ApiReponse<List<AllRegistersResponse>> response = new ApiReponse<>("Registers found", getRegister.getAllRegisters());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/byMonth")
    ResponseEntity<ApiReponse<RegisterByMonthResponse>> getUserRegistersByMonth(
            @RequestParam(name = "date", required = false) LocalDate date,
            @RequestHeader("Authorization") String token) {
        LocalDate monthParam = (date == null) ? LocalDate.now() : date;
        ApiReponse<RegisterByMonthResponse> response = new ApiReponse<>("Registers found", getRegister.getUserRegistersByMonth(token, monthParam));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("{id}")
    ResponseEntity<ApiReponse<RegisterResponse>> updateResponse(
            @PathVariable("id") int id,
            @RequestBody CreateRegisterRequest request,
            @RequestHeader("Authorization") String token) throws Exception {
        ApiReponse<RegisterResponse> response = new ApiReponse<>(
                "Register edited successfully",
                updateRegister.updateRegister(id, request, token));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("{id}")
    ResponseEntity<ApiReponse<Void>> deleteResponse(
            @PathVariable("id") int id,
            @RequestHeader("Authorization") String token) throws Exception {

        deleteRegister.deleteRegister(id, token);

        ApiReponse<Void> response = new ApiReponse<>(
                "Registed deleted successfully",
                null
                );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}

