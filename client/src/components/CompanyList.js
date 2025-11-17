import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { companyService } from '../services/api';

function CompanyList() {
  const [companies, setCompanies] = useState([]);
  const [filteredCompanies, setFilteredCompanies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedDomain, setSelectedDomain] = useState('ALL');

  const domains = ['ALL', 'IT', 'AI', 'BANQUE', 'ASSURANCE', 'TELECOM', 'ENERGIE', 'SANTE', 'AUTOMOBILE', 'AUTRE'];

  useEffect(() => {
    loadCompanies();
  }, []);

  useEffect(() => {
    if (selectedDomain === 'ALL') {
      setFilteredCompanies(companies);
    } else {
      setFilteredCompanies(companies.filter(c => c.domain === selectedDomain));
    }
  }, [selectedDomain, companies]);

  const loadCompanies = async () => {
    try {
      const response = await companyService.getAll();
      setCompanies(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error loading companies:', error);
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer cette entreprise ?')) {
      try {
        await companyService.delete(id);
        loadCompanies();
      } catch (error) {
        console.error('Error deleting company:', error);
        alert('Erreur lors de la suppression');
      }
    }
  };

  if (loading) {
    return <div className="loading">Chargement...</div>;
  }

  return (
    <div>
      <div className="page-header">
        <h2 className="page-title">Entreprises Cotées</h2>
        <Link to="/company/new" className="btn btn-primary">
          + Nouvelle Entreprise
        </Link>
      </div>

      <div className="filter-container">
        <div className="filter-group">
          <div className="form-group" style={{ marginBottom: 0, flex: 1 }}>
            <label className="form-label">Filtrer par domaine:</label>
            <select 
              className="form-select"
              value={selectedDomain}
              onChange={(e) => setSelectedDomain(e.target.value)}
            >
              {domains.map(domain => (
                <option key={domain} value={domain}>{domain}</option>
              ))}
            </select>
          </div>
        </div>
      </div>

      {filteredCompanies.length === 0 ? (
        <div className="empty-state">
          <p>Aucune entreprise trouvée</p>
        </div>
      ) : (
        filteredCompanies.map(company => (
          <div key={company.id} className="card">
            <div className="card-header">
              <h3 className="card-title">{company.name}</h3>
              <div className="card-actions">
                <button 
                  onClick={() => handleDelete(company.id)}
                  className="btn btn-danger"
                >
                  Supprimer
                </button>
              </div>
            </div>
            <div className="card-info">
              <div className="info-item">
                <span className="info-label">Date d'introduction</span>
                <span className="info-value">{new Date(company.ipoDate).toLocaleDateString('fr-FR')}</span>
              </div>
              <div className="info-item">
                <span className="info-label">Prix actuel</span>
                <span className="info-value" style={{ color: '#48bb78', fontSize: '1.25rem' }}>
                  {company.currentStockPrice.toFixed(2)} €
                </span>
              </div>
              <div className="info-item">
                <span className="info-label">Domaine</span>
                <span className={`badge badge-${company.domain}`}>{company.domain}</span>
              </div>
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default CompanyList;
