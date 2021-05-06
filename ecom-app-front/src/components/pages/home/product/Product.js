import axios from 'axios';
import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './Product.css';

class Product extends Component {

	 addToCart = async(e) => {
		try {
			const res = await axios.post('/api/cart', {
				user_id: 1,
				item_id: e.target.value,
				quantity: 1
			});
			console.log(res)
		} catch (error) {
			console.log(error);
		}
	}

	render() {
		return (
			<div className="product">
				<Link to={`/product/${this.props.id}`} >
					<img src={require(`../../../../assets/products/${this.props.image}`)} alt="" />
				</Link>
				<p className="title">{this.props.title}</p>
				<div className="rating">
					<i className="fas fa-star" />
					<i className="fas fa-star" />
					<i className="fas fa-star" />
					<i className="fas fa-star" />
					<i className="far fa-star" />
				</div>
				<p className="price">${this.props.price.toFixed(2)}</p>
				<button className="atc" value={this.props.id} onClick={this.addToCart}>Add to Cart</button>
			</div>
		);
	}
}

export default Product;
