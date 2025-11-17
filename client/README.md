# Stock Market Client

Application React pour gérer les entreprises cotées en bourse et leurs cotations.

## Installation

```bash
npm install
```

## Démarrage

```bash
npm start
```

L'application sera accessible sur http://localhost:3000

## Configuration

L'API backend doit être accessible sur http://localhost:8888 (Gateway Service)

Pour changer l'URL de l'API, modifier le fichier `.env`:
```
REACT_APP_API_URL=http://localhost:8888
```

## Fonctionnalités

- ✅ Gestion des entreprises cotées
- ✅ Consultation et filtrage par domaine
- ✅ Ajout de nouvelles entreprises
- ✅ Suppression d'entreprises
- ✅ Gestion des cotations boursières
- ✅ Consultation et filtrage par entreprise
- ✅ Ajout de nouvelles cotations
- ✅ Mise à jour automatique du prix de l'action

## Structure

```
src/
  ├── components/
  │   ├── CompanyList.js      # Liste des entreprises
  │   ├── CompanyForm.js      # Formulaire d'ajout
  │   ├── StockList.js        # Liste des cotations
  │   └── StockForm.js        # Formulaire de cotation
  ├── services/
  │   └── api.js              # Services API
  ├── App.js                  # Composant principal
  └── App.css                 # Styles
```
