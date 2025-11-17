import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { stockService, companyService } from '../services/api';

function StockForm() {
  const navigate = useNavigate();
  const [companies, setCompanies] = useState([]);
  const [formData, setFormData] = useState({
    date: new Date().toISOString().split('T')[0],
    openValue: '',
    highValue: '',
    lowValue: '',
    closeValue: '',
    volume: '',
    companyId: ''
  });

  useEffect(() => {
    loadCompanies();
  }, []);

  const loadCompanies = async () => {
    try {
      const response = await companyService.getAll();
      setCompanies(response.data);
      if (response.data.length > 0) {
        setFormData(prev => ({ ...prev, companyId: response.data[0].id }));
      }
    } catch (error) {
      console.error('Error loading companies:', error);
    }
  };

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await stockService.create({
        ...formData,
        openValue: parseFloat(formData.openValue),
        highValue: parseFloat(formData.highValue),
        lowValue: parseFloat(formData.lowValue),
        closeValue: parseFloat(formData.closeValue),
        volume: parseInt(formData.volume),
        companyId: parseInt(formData.companyId)
      });
      navigate('/stocks');
    } catch (error) {
      console.error('Error creating stock:', error);
      alert('Erreur lors de la création de la cotation');
    }
  };

  if (companies.length === 0) {
    return (
      <div className="empty-state">
        <p>Aucune entreprise disponible. Veuillez d'abord créer une entreprise.</p>
        <button onClick={() => navigate('/company/new')} className="btn btn-primary" style={{ marginTop: '1rem' }}>
          Créer une entreprise
        </button>
      </div>
    );
  }

  return (
    <div>
      <div className="page-header">
        <h2 className="page-title">Nouvelle Cotation</h2>
      </div>

      <form className="form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label className="form-label">Entreprise *</label>
          <select
            name="companyId"
            className="form-select"
            value={formData.companyId}
            onChange={handleChange}
            required
          >
            {companies.map(company => (
              <option key={company.id} value={company.id}>
                {company.name}
              </option>
            ))}
          </select>
        </div>

        <div className="form-group">
          <label className="form-label">Date *</label>
          <input
            type="date"
            name="date"
            className="form-input"
            value={formData.date}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Prix d'ouverture (€) *</label>
          <input
            type="number"
            name="openValue"
            className="form-input"
            step="0.01"
            min="0"
            value={formData.openValue}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Plus haut (€) *</label>
          <input
            type="number"
            name="highValue"
            className="form-input"
            step="0.01"
            min="0"
            value={formData.highValue}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Plus bas (€) *</label>
          <input
            type="number"
            name="lowValue"
            className="form-input"
            step="0.01"
            min="0"
            value={formData.lowValue}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Prix de clôture (€) *</label>
          <input
            type="number"
            name="closeValue"
            className="form-input"
            step="0.01"
            min="0"
            value={formData.closeValue}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label className="form-label">Volume *</label>
          <input
            type="number"
            name="volume"
            className="form-input"
            min="0"
            value={formData.volume}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-actions">
          <button type="submit" className="btn btn-primary">
            Créer
          </button>
          <button 
            type="button" 
            className="btn" 
            onClick={() => navigate('/stocks')}
            style={{ background: '#e2e8f0', color: '#2d3748' }}
          >
            Annuler
          </button>
        </div>
      </form>
    </div>
  );
}

export default StockForm;
