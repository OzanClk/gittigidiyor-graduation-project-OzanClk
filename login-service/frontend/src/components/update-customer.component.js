import React, {Component} from 'react';
import UserService from "../services/user.service";
import {Button, Form, FormGroup, Label} from "reactstrap";

class UpdateCustomerComponent extends Component {


    constructor(props) {
        super(props)

        this.state = {

            id: this.props.match.params.id,
            firstname: '',
            lastname: '',
            email: '',
            phoneNumber: '',
            country: '',
            city: '',
            district: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePhoneNumberHandler = this.changePhoneNumberHandler.bind(this);
        this.changeCountryHandler = this.changeCountryHandler.bind(this);
        this.changeCityHandler = this.changeCityHandler.bind(this);
        this.changeDistrictHandler = this.changeDistrictHandler.bind(this);
        this.updateCustomer = this.updateCustomer.bind(this);

    }

    componentDidMount() {
        UserService.getCustomersById(this.state.id).then(response => {
            let customer = response.data;
            console.log(customer.id);
            this.setState({
                firstname: customer.firstname, lastname: customer.lastname
                , email: customer.email, phoneNumber: customer.phoneNumber, country: customer.address[0].country,
                city: customer.address[0].city, district: customer.address[0].district
            });
        });

    }

    updateCustomer = (e) => {
        e.preventDefault();
        let customer = {
            id: this.state.id,
            firstname: this.state.firstname,
            lastname: this.state.lastname,
            email: this.state.email,
            phoneNumber: this.state.phoneNumber,
            address: {
                country: this.state.country,
                city: this.state.city,
                district: this.state.district
            }
        };
        console.log(JSON.stringify(customer));

        UserService.saveCustomers(customer).then(res => {
            this.props.history.push('/admin')
        })

    }




    changeFirstNameHandler = (event) => {
        this.setState({firstname: event.target.value});
    }

    changeLastNameHandler = (event) => {
        this.setState({lastname: event.target.value});
    }

    changeEmailHandler = (event) => {
        this.setState({email: event.target.value});
    }
    changePhoneNumberHandler = (event) => {
        this.setState({phoneNumber: event.target.value});
    }
    changeCountryHandler = (event) => {
        this.setState({country: event.target.value});
    }
    changeCityHandler = (event) => {
        this.setState({city: event.target.value});
    }
    changeDistrictHandler = (event) => {
        this.setState({district: event.target.value});
    }


    cancel() {
        this.props.history.push("/admin")
    }

    render() {
        return (
            <div>
                <div>
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">

                                <h3 className="text-center">Add Customer</h3>
                                <div className="card-body">
                                    <Form>
                                        <FormGroup>
                                            <Label>FirstName</Label>
                                            <input placeholder="FirstName" name="firstname" className="form-control"
                                                   value={this.state.firstname} onChange={this.changeFirstNameHandler}/>
                                        </FormGroup>
                                        <FormGroup>
                                            <Label>LastName</Label>
                                            <input placeholder="LastName" name="lastname" className="form-control"
                                                   value={this.state.lastname} onChange={this.changeLastNameHandler}/>
                                        </FormGroup>
                                        <FormGroup>
                                            <Label>E-mail</Label>
                                            <input placeholder="E-mail" name="email" className="form-control"
                                                   value={this.state.email} onChange={this.changeEmailHandler}/>
                                        </FormGroup>
                                        <FormGroup>
                                            <Label>PhoneNumber</Label>
                                            <input placeholder="PhoneNumber" name="phoneNumber" className="form-control"
                                                   value={this.state.phoneNumber}
                                                   onChange={this.changePhoneNumberHandler}/>
                                        </FormGroup>
                                        <FormGroup>
                                            <Label>Country</Label>
                                            <input placeholder="Country" name="country" className="form-control"
                                                   value={this.state.country}
                                                   onChange={this.changeCountryHandler}/>
                                        </FormGroup>
                                        <FormGroup>
                                            <Label>City</Label>
                                            <input placeholder="City" name="city" className="form-control"
                                                   value={this.state.city}
                                                   onChange={this.changeCityHandler}/>
                                        </FormGroup>
                                        <FormGroup>
                                            <Label>District</Label>
                                            <input placeholder="District" name="district" className="form-control"
                                                   value={this.state.district}
                                                   onChange={this.changeDistrictHandler}/>
                                        </FormGroup>
                                        <Button color="success" onClick={this.updateCustomer}>Save</Button>
                                        <Button color="danger" onClick={this.cancel.bind(this)}
                                                style={{marginLeft: "15px"}}>Cancel</Button>
                                    </Form>


                                </div>


                            </div>
                        </div>

                    </div>

                </div>
            </div>
        );
    }
}

export default UpdateCustomerComponent;