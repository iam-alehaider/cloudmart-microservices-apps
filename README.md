# 🛒 Retail Store Cloud-Native Microservices Platform (AWS + EKS + GitOps)

## 📌 Project Overview

This project is a production-style cloud-native microservices platform that simulates
a real-world retail e-commerce system deployed on AWS using Kubernetes.

The platform demonstrates modern DevOps and Cloud practices including:

- Microservices architecture
- Kubernetes on Amazon EKS
- GitOps deployments using ArgoCD
- Infrastructure as Code using Terraform
- CI/CD pipelines using GitHub Actions
- Secure AWS authentication using OIDC (no access keys in CI)

This repository is the **parent documentation repo** that explains the full system
architecture and workflow.

---

## 🧩 Microservices Architecture

The application is intentionally designed as multiple de-coupled services.

| Service  | Language | Description |
|--------|----------|------------|
| UI | Java | Web frontend |
| Catalog | Go | Product catalog API |
| Cart | Java | Shopping cart API |
| Orders | Java | Order management API |
| Checkout | Node.js | Checkout orchestration API |

Each service:
- Has its own Docker image
- Has its own Helm chart
- Is deployed independently

---

## ☁️ Infrastructure Architecture

### Platform

- AWS VPC with public and private subnets
- Amazon EKS cluster
- Managed node groups
- NGINX Ingress Controller
- cert-manager (optional TLS)
- Cluster add-ons via Terraform

### Security

- IAM roles for service accounts (IRSA)
- GitHub Actions authenticated via AWS OIDC
- No static AWS keys in CI/CD
- Least-privilege IAM policies

---

## 🔁 GitOps Deployment Model

All Kubernetes deployments are managed using GitOps.

Flow:

Developer Push → GitHub Actions → GitOps Repo → ArgoCD → EKS Cluster

ArgoCD continuously monitors the GitOps repository and syncs changes automatically.

No manual kubectl deployments are used.

---

## 🔄 CI/CD Pipeline Flow

Each microservice has its own GitHub Actions workflow.

Pipeline stages:

1. Trigger on push to `prod` branch
2. Build Docker image
3. Authenticate to AWS using OIDC
4. Push image to Amazon ECR
5. Update image tag in GitOps repository
6. Commit GitOps change
7. ArgoCD deploys automatically

This provides continuous delivery with full traceability.

---

## 📦 Container Registry

Private Amazon ECR repositories:

- demo-private/ui
- demo-private/catalog
- demo-private/cart
- demo-private/orders
- demo-private/checkout

Each service pushes to its own repository.

---

## 📁 Repository Structure

This platform uses three functional repositories.

### 🧱 Infrastructure (Terraform)

Repo: **retail-store-infra**

Responsible for:

- VPC
- EKS cluster
- IAM roles
- OIDC provider
- ArgoCD installation
- Cluster add-ons

---

### 🚀 Application & CI

Repo: **retail-store-app**

Contains:

- Microservice source code
- Dockerfiles
- Helm charts
- GitHub Actions pipelines

Responsible for building and publishing images.

---

### 🔁 GitOps Deployment State

Repo: **retail-store-gitops**

Contains:

- Helm values
- Environment configs
- ArgoCD application definitions

Represents the desired state of Kubernetes.

---

## 🎯 Skills Demonstrated

This project demonstrates:

- AWS cloud architecture
- Kubernetes production deployment
- Terraform Infrastructure as Code
- Secure CI/CD using OIDC
- GitOps operational model
- Microservices system design
- Observability-ready workloads

---

## 👤 Author

Ali Haider  
IT Infrastructure & Cloud Engineer  
Linux | AWS | Kubernetes | Terraform | DevOps Automation  

GitHub: https://github.com/iam-alehaider
