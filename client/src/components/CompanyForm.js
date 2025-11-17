import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { companyService } from '../services/api';

function CompanyForm() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    ipoDate: '',
    currentStockPrice: '',
    domain: 'IT'
  });

  const domains = ['IT', 'AI', 'BANQUE', 'ASSURANCE', 'TELECOM', 'ENERGIE', 'SANTE', 'AUTOMOBILE', 'AUTRE'];

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await companyService.create({
        ...formData,
        currentStockPrice: parseFloat(formData.currentStockPrice)
      });
      navigate('/');
    } catch (error) {
      console.error('Error creating company:', error);
      alert('Erreur lors de la création de l\'entreprise');
    }
  };

  return (
    <div>
      <div className="page-header">
        <h2 className="page-title">Nouvelle Entreprise</h2>
      </div>

      <form className="form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label className="form-label">Nom de l'entreprise *</label>
          <input
            type="text"
            name="name"
            className="form-input"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Date d'introduction en bourse *</label>
          <input
            type="date"
            name="ipoDate"
            className="form-input"
            value={formData.ipoDate}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Prix actuel de l'action (€) *</label>
          <input
            type="number"
            name="currentStockPrice"
            className="form-input"
            step="0.01"
            min="0"
            value={formData.currentStockPrice}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Domaine *</label>
          <select
            name="domain"
            className="form-select"
            value={formData.domain}
            onChange={handleChange}
            required
          >
            {domains.map(domain => (
              <option key={domain} value={domain}>{domain}</option>
            ))}
          </select>
        </div>

        <div className="form-actions">
          <button type="submit" className="btn btn-primary">
            Créer
          </button>
          <button 
            type="button" 
            className="btn" 
            onClick={() => navigate('/')}
            style={{ background: '#e2e8f0', color: '#2d3748' }}
          >
            Annuler
          </button>
        </div>
      </form>
    </div>
  );
}

export default CompanyForm;
