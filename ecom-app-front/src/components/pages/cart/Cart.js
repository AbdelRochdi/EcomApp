import React, { Component } from 'react';
import CartItem from './cartItem/CartItem';
import './Cart.css';

class Cart extends Component {
	render() {
		return (
			<div className="cart__container">
				<div className="cartItems">
					<p className="cartItems__title">Shopping Cart</p>
					<CartItem />
					<CartItem />
				</div>
				<div className="subtotal">
					<div className="subtotal__info">
						<p>&#10004; Your order is eligible for FREE Delivery.</p>
					</div>
					<div className="ptb">
						<p className="ptb__number">Subtotal ( 2 item):</p>
						<p className="ptb__total">$304.00</p>
						<button className="ptb__btn">Proceed to Buy</button>
					</div>
				</div>
			</div>
		);
	}
}

export default Cart;
