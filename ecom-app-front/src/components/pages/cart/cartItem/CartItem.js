import React, { Component } from 'react';
import './CartItem.css';
import Redmi from '../../../../assets/products/2.png';

export class CartItem extends Component {
	render() {
		return (
			<div className="cartitem">
				<div className="cartitem__image">
					<img src={Redmi} alt="" />
				</div>
				<div className="cartitem__infos">
					<div className="infos-header">
						<p className="infos-header__title">Apple</p>
						<p className="infos-header__price">$152.00</p>
					</div>
					<p className="infos__brand">by Apple</p>
					<div className="rating rating--resize">
						<i className="fas fa-star" />
						<i className="fas fa-star" />
						<i className="fas fa-star" />
						<i className="fas fa-star" />
						<i className="far fa-star" /> <span>20,534 ratings</span>
					</div>
					<div className="cartitem__actions">
						<div className="quantity quantity--resize">
							<div>
								<i className="fas fa-chevron-up" />
							</div>
							<div>1</div>
							<div>
								<i className="fas fa-chevron-down" />
							</div>
						</div>
						<p className="delete">Delete</p>
						<p className="save">Save for Later</p>
					</div>
				</div>
			</div>
		);
	}
}

export default CartItem;
