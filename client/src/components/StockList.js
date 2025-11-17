import React, { useState, useEffect } from 'react';
import { stockService, companyService } from '../services/api';

function StockList() {
  const [stocks, setStocks] = useState([]);
  const [companies, setCompanies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedCompany, setSelectedCompany] = useState('ALL');

  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      const [stocksRes, companiesRes] = await Promise.all([
        stockService.getAll(),
        companyService.getAll()
      ]);
      setStocks(stocksRes.data);
      setCompanies(companiesRes.data);
      setLoading(false);
    } catch (error) {
      console.error('Error loading data:', error);
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer cette cotation ?')) {
      try {
        await stockService.delete(id);
        loadData();
      } catch (error) {
        console.error('Error deleting stock:', error);
        alert('Erreur lors de la suppression');
      }
    }
  };

  const getCompanyName = (companyId) => {
    const company = companies.find(c => c.id === companyId);
    return company ? company.name : `ID: ${companyId}`;
  };

  const filteredStocks = selectedCompany === 'ALL' 
    ? stocks 
    : stocks.filter(s => s.companyId === parseInt(selectedCompany));

  if (loading) {
    return <div className="loading">Chargement...</div>;
  }

  return (
    <div>
      <div className="page-header">
        <h2 className="page-title">Cotations Boursières</h2>
        <a href="/stock/new" className="btn btn-primary">
          + Nouvelle Cotation
        </a>
      </div>

      <div className="filter-container">
        <div className="filter-group">
          <div className="form-group" style={{ marginBottom: 0, flex: 1 }}>
            <label className="form-label">Filtrer par entreprise:</label>
            <select 
              className="form-select"
              value={selectedCompany}
              onChange={(e) => setSelectedCompany(e.target.value)}
            >
              <option value="ALL">Toutes les entreprises</option>
              {companies.map(company => (
                <option key={company.id} value={company.id}>{company.name}</option>
              ))}
            </select>
          </div>
        </div>
      </div>

      {filteredStocks.length === 0 ? (
        <div className="empty-state">
          <p>Aucune cotation trouvée</p>
        </div>
      ) : (
        filteredStocks.map(stock => (
          <div key={stock.id} className="card">
            <div className="card-header">
              <div>
                <h3 className="card-title">{getCompanyName(stock.companyId)}</h3>
                <p style={{ color: '#718096', fontSize: '0.875rem', marginTop: '0.25rem' }}>
                  {new Date(stock.date).toLocaleDateString('fr-FR')}
                </p>
              </div>
              <div className="card-actions">
                <button 
                  onClick={() => handleDelete(stock.id)}
                  className="btn btn-danger"
                >
                  Supprimer
                </button>
              </div>
            </div>
            <div className="card-info">
              <div className="info-item">
                <span className="info-label">Ouverture</span>
                <span className="info-value">{stock.openValue.toFixed(2)} €</span>
              </div>
              <div className="info-item">
                <span className="info-label">Plus haut</span>
                <span className="info-value" style={{ color: '#48bb78' }}>
                  {stock.highValue.toFixed(2)} €
                </span>
              </div>
              <div className="info-item">
                <span className="info-label">Plus bas</span>
                <span className="info-value" style={{ color: '#f56565' }}>
                  {stock.lowValue.toFixed(2)} €
                </span>
              </div>
              <div className="info-item">
                <span className="info-label">Clôture</span>
                <span className="info-value" style={{ fontWeight: 'bold' }}>
                  {stock.closeValue.toFixed(2)} €
                </span>
              </div>
              <div className="info-item">
                <span className="info-label">Volume</span>
                <span className="info-value">{stock.volume.toLocaleString('fr-FR')}</span>
              </div>
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default StockList;
