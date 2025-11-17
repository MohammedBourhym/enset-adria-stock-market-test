import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import CompanyList from './components/CompanyList';
import CompanyForm from './components/CompanyForm';
import StockList from './components/StockList';
import StockForm from './components/StockForm';
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <nav className="navbar">
          <div className="nav-container">
            <h1 className="nav-logo">📈 Stock Market</h1>
            <ul className="nav-menu">
              <li className="nav-item">
                <Link to="/" className="nav-link">Companies</Link>
              </li>
              <li className="nav-item">
                <Link to="/stocks" className="nav-link">Stocks</Link>
              </li>
            </ul>
          </div>
        </nav>

        <div className="container">
          <Routes>
            <Route path="/" element={<CompanyList />} />
            <Route path="/company/new" element={<CompanyForm />} />
            <Route path="/company/edit/:id" element={<CompanyForm />} />
            <Route path="/stocks" element={<StockList />} />
            <Route path="/stock/new" element={<StockForm />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
