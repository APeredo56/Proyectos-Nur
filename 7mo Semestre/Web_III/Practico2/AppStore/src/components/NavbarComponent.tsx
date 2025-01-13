import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import { Category } from "../models/Category";
import { CategoryService } from "../services/CategoryService";
import { useEffect, useState } from "react";
import { Routes } from "../routes/CONSTANTS";

const NavbarComponent = () => {
  const [categories, setCategories] = useState<Category[]>([])

  const fetchCategories = () => {
    CategoryService.list().then((response) => {
      setCategories(response);
    }).catch((error) => {
      console.log(error);
    });
  }

  useEffect(() => {
    fetchCategories();
  }, []);

  return (
    <Navbar bg="dark" data-bs-theme="dark" className="border-bottom border-2">
      <Container>
        <Navbar.Brand href={Routes.HOME}>AppStore</Navbar.Brand>
        <Nav className="me-auto">
          <NavDropdown title="Categorías" id="navbarScrollingDropdown">
            {categories.map((category) => (
              <NavDropdown.Item href={Routes.CATEGORIES.DETAIL_PARAM(category.id)}
                key={"category-nav-" + category.id}>
                {category.name}
              </NavDropdown.Item>
            ))}
          </NavDropdown>
        </Nav>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent;