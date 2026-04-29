package mate.academy.springbootbookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.springbootbookstore.dto.cartItem.CreateCartItemRequestDto;
import mate.academy.springbootbookstore.dto.cartItem.UpdateCartItemRequestDto;
import mate.academy.springbootbookstore.dto.shoppingCart.ShoppingCartDto;
import mate.academy.springbootbookstore.service.shoppingCart.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Shopping cart management",
        description = "Endpoints for managing shopping cart and cart items"
)
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @Operation(
            summary = "Get current user's shopping cart",
            description = "Returns shopping cart with all books for authenticated user"
    )
    public ShoppingCartDto showShoppingCart() {
        return shoppingCartService.showShoppingCart();
    }

    @PostMapping
    @Operation(
            summary = "Add book to shopping cart",
            description = "Adds a new book or increases quantity if it already exists in cart"
    )
    public ShoppingCartDto addCartItem(@RequestBody @Valid CreateCartItemRequestDto requestDto) {
        return shoppingCartService.addCartItem(requestDto);
    }

    @PutMapping("/items/{cartItemId}")
    @Operation(
            summary = "Update quantity of a cart item",
            description = "Updates quantity of a specific book in shopping cart"
    )
    public ShoppingCartDto updateCartItem (
            @PathVariable Long cartItemId,
            @RequestBody UpdateCartItemRequestDto requestDto
    ) {
        return shoppingCartService.updateCartItem(cartItemId, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/items/{cartItemId}")
    @Operation(
            summary = "Remove book from shopping cart",
            description = "Deletes a cart item by its ID"
    )
    public void deleteCartItem(Long cartItemId) {
        shoppingCartService.deleteCartItem(cartItemId);
    }
}
