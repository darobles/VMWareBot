### Heading level 3

Install terraform in linux
<pre><code>
sudo yum install -y yum-utils
</code></pre>
<pre><code>
sudo yum-config-manager --add-repo https://rpm.releases.hashicorp.com/RHEL/hashicorp.repo
</code></pre>
<pre><code>
sudo yum -y install terraform
</code></pre>
<pre><code>
terraform -v
</code></pre>

Run the script
<pre><code>
terraform init
terraform plan
terraform apply
</code></pre>


