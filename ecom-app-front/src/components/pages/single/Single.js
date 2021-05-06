import React, { Component } from 'react';
import {Link} from 'react-router-dom'
import axios from 'axios';
import './Single.css';

class Single extends Component {
	state = {
		product: {},
		loading: true
	};

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

	async componentDidMount() {
		try {
			const res = await axios.get(`/api/products/${this.props.match.params.id}`);
			this.setState({ product: res.data, loading: false });
			console.log(res.data);
		} catch (error) {
			console.log(error);
		}
	}

	render() {
		const name = this.state.product.item_name;
		const id = this.state.product.item_id;
		const image = this.state.product.item_image;
		const price = this.state.product.item_price;
		const brand = this.state.product.item_brand;

		if (this.state.loading === true) {
			return 'page loading ...';
		}

		return (
			<div className="single-wrapper">
				<div className="showcase">
					<div className="product-image">
						<img src={require(`../../../assets/products/${image}`)} alt="" />
					</div>
					<div className="buttons">
						<Link to="/cart">  <button className="ptc">Proceed to Checkout </button> </Link>
						  <button className="atc2" value={id} onClick={this.addToCart}>Add to Cart</button>
					</div>
				</div>
				<div className="infos">
					<h3 className="infos__title">{name}</h3>
					<h5 className="brand">by {brand}</h5>
					<div className="rating">
						<i className="fas fa-star" />
						<i className="fas fa-star" />
						<i className="fas fa-star" />
						<i className="fas fa-star" />
						<i className="far fa-star" /> <span>20,534 ratings | 1000+ answered questions</span>
					</div>
					<div className="prices">
						<p className="mrp">
							M.R.P: <span>$162.00</span>
						</p>
						<p className="price">
							Deal Price: <span>${price.toFixed(2)}</span> Inclusive of all taxes
						</p>
						<p className="saving">
							You Save: <span>$12.00</span>
						</p>
					</div>
					<div className="services">
						<div>
							<i className="fas fa-sync" />
							<p>10 Days Replacement</p>
						</div>
						<div>
							<i className="fas fa-shipping-fast" />
							<p>Fast Delivery</p>
						</div>
						<div>
							<i className="fas fa-check-double" />
							<p>1 Year Warranty</p>
						</div>
					</div>
					<div className="delivery">
						<p>Delivered in 3 to 10 Days</p>
						<p>Sold by Daily Electronics (4.5 out of 5 | 18,198 ratings)</p>
					</div>

					<div className="quantity">
						<span>Qty</span>
						<div>
							<i className="fas fa-chevron-up" />
						</div>
						<div>1</div>
						<div>
							<i className="fas fa-chevron-down" />
						</div>
					</div>
					<div className="size">
						<h4>Size:</h4>
						<div className="sizes">
							<div>4GB RAM</div>
							<div>6GB RAM</div>
							<div>8GB RAM</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}

export default Single;
