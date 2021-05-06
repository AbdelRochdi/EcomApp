import React, { Component, Fragment } from 'react';
import './Home.css';
import banner from '../../../assets/Banner1.png';
import Product from './product/Product';
import axios from 'axios';

class Home extends Component {
	state = {
		products: [],
		filteredList: [],
		loading: true
	};

	async componentDidMount() {
		try {
			const res = await axios.get('/api/products');
			this.setState({ products: res.data, loading: false });
			console.log(res.data);
		} catch (error) {
			console.log(error);
		}
	}

	filterProducts = (e) => {
		let filter = this.state.products.filter((product) => {
			return product.item_brand === e.target.textContent;
		});

		this.setState({ filteredList: filter });
	};

	render() {
		let filteredProducts = this.state.filteredList.map((product) => {
			return (
				<Product
					key={product.item_id}
					id={product.item_id}
					title={product.item_name}
					price={product.item_price}
					image={product.item_image}
				/>
			);
		});

		let products = this.state.products.map((product) => {
			return (
				<Product
					key={product.item_id}
					id={product.item_id}
					title={product.item_name}
					price={product.item_price}
					image={product.item_image}
				/>
			);
		});

		if (this.state.loading === true) {
			return 'page is loading';
		}

		return (
			<Fragment>
				{' '}
				<div className="hero">
					<img src={banner} alt="" />
				</div>
				<section className="product-container">
					<div className="section-header">
						<h3 className="section-title">Our Products</h3>
						<ul className="filter">
							<li onClick={this.filterProducts}>All Brand</li>
							<li onClick={this.filterProducts}>Apple</li>
							<li onClick={this.filterProducts}>Redmi</li>
							<li onClick={this.filterProducts}>Samsung</li>
						</ul>
					</div>
					<div className="products">{this.state.filteredList.length > 0 ? filteredProducts : products}</div>
				</section>
			</Fragment>
		);
	}
}

export default Home;
